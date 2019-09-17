package com.github.apuex.springbootsolution.codegen


object GenerateAll extends App {
  Project.main(args)
  App.main(args)
  Controller.main(args)
  ApiList.main(args)
  Dao.main(args)
  CassandraDao.main(args)
  SqlServerSchemaGenerator.main(args)
  MysqlSchemaGenerator.main(args)
  Message.main(args)
  Service.main(args)
}
