-- CreateTable
CREATE TABLE "finances" (
    "Cod_Finance" TEXT NOT NULL PRIMARY KEY,
    "Name" TEXT NOT NULL,
    "Amount" REAL NOT NULL,
    "Day" INTEGER NOT NULL,
    "Type" TEXT NOT NULL
);
