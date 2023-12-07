import { Controller, Get } from '@nestjs/common';
import Handlebars from 'handlebars';

import * as fs from 'fs';
import * as path from 'path';
import { PrismaService } from './services/prisma.service';

function toMoney(amount: number): string {
  return amount.toLocaleString('pt-br', {
    style: 'currency',
    currency: 'BRL',
  });
}

@Controller()
export class AppController {
  constructor(private prisma: PrismaService) {}

  @Get()
  async list(): Promise<string> {
    const finances = await this.prisma.finances.findMany();

    type InDay = { Day: number; Summary: string; Amount: number };

    const days = new Array(31).fill('').map(
      (_, index): InDay => ({
        Amount: 0,
        Day: index + 1,
        Summary: '',
      }),
    );

    finances.forEach((finance) => {
      if (finance.Type === 'INCOME') {
        for (let i = finance.Day - 1; i < days.length; i++) {
          days[i].Amount += finance.Amount;
        }
      } else if (finance.Type === 'OUTCOME') {
        for (let i = finance.Day - 1; i < days.length; i++) {
          days[i].Amount -= finance.Amount;
        }
      }
    });

    days.forEach((day) => {
      day.Summary = finances
        .filter((fin) => fin.Day === day.Day)
        .map((fin) => fin.Name)
        .join(', ');

      (day.Amount as any) = toMoney(day.Amount);
    });

    const template = Handlebars.compile(
      fs.readFileSync(path.resolve('public', 'index.hbs'), {
        encoding: 'utf-8',
      }),
    );

    const lastDay = days[days.length - 1];

    return template({
      days,
      spendAmount: lastDay ? toMoney(lastDay.Amount) : '',
      incomes: finances
        .filter((i) => i.Type === 'INCOME')
        .map((i) => ({ ...i, Amount: toMoney(i.Amount) })),
      outcomes: finances
        .filter((i) => i.Type === 'OUTCOME')
        .map((i) => ({ ...i, Amount: toMoney(i.Amount) })),
    });
  }
}
