import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { ServeStaticModule } from '@nestjs/serve-static';

import * as path from 'path';
import { PrismaService } from './services/prisma.service';

@Module({
  imports: [
    ServeStaticModule.forRoot({
      rootPath: path.join(__dirname, '..', 'public'),
    }),
  ],
  controllers: [AppController],
  providers: [PrismaService],
})
export class AppModule {}
