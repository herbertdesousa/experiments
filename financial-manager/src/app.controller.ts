import { Controller, Get } from '@nestjs/common';
import { Finance } from './model/Finance';
import Handlebars from 'handlebars';

import * as fs from 'fs';
import * as path from 'path';

function toMoney(amount: number): string {
  return amount.toLocaleString('pt-br', {
    style: 'currency',
    currency: 'BRL',
  });
}

@Controller()
export class AppController {
  constructor() {}

  @Get()
  async list(): Promise<string> {
    const finance1 = new Finance();
    finance1.Cod_Finance = new Date(Date.now()).toISOString();
    finance1.Name = '1 parcela';
    finance1.Type = 'INCOME';
    finance1.Amount = 1259.43;
    finance1.Day = 5;

    const finance3 = new Finance();
    finance3.Cod_Finance = new Date(Date.now()).toISOString();
    finance3.Name = '2 parcela';
    finance3.Type = 'INCOME';
    finance3.Amount = 2600.43;
    finance3.Day = 5;

    const finance2 = new Finance();
    finance2.Cod_Finance = new Date(Date.now()).toISOString();
    finance2.Name = 'Faculdade';
    finance2.Type = 'OUTCOME';
    finance2.Amount = 2440;
    finance2.Day = 20;

    const finances = [finance1, finance3, finance2];

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

    return template({
      days,
      incomes: finances.filter((i) => i.Type === 'INCOME'),
      outcomes: finances.filter((i) => i.Type === 'OUTCOME'),
    });
  }
}
