create extension hstore;
create schema games;
create table if not exists games."Game" ("id" BIGSERIAL NOT NULL PRIMARY KEY,"playerturn" BIGSERIAL NOT NULL,"mesh" VARCHAR NOT NULL,"piecesOutList" INTEGER NOT NULL, "timesPlayerRolledList" INTEGER NOT NULL);