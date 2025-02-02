package com.github.apuex.springbootsolution.runtime

import com.github.apuex.springbootsolution.runtime.DateFormat._
import com.github.apuex.springbootsolution.runtime.SymbolConverters._
import com.google.protobuf.util.Timestamps

trait TypeConverter {
  def convert(value: String): Any
}

object TypeConverters {
  def toJavaTypeConverter(typeName: String): TypeConverter = typeName match {
    case "bool" => v => v.toBoolean
    case "short" => v => v.toShort
    case "byte" => v => v.toByte
    case "int" => v => v.toInt
    case "long" => v => v.toLong
    case "decimal" => v => BigDecimal.apply(v)
    case "string" => v => v
    case "text" => v => v
    case "timestamp" => v => toDate(Timestamps.parse(v))
    case "float" => v => v.toFloat
    case "double" => v => v.toDouble
    case "blob" => v => v
    case _ =>
      v => cToPascal(v)
  }

  def toJdbcType(typeName: String): String = typeName match {
    case "bool" => "boolean"
    case "short" => "short"
    case "byte" => "byte"
    case "int" => "int"
    case "long" => "long"
    case "decimal" => "BigDecimal"
    case "string" => "String"
    case "text" => "String"
    case "timestamp" => "Timestamp"
    case "float" => "float"
    case "double" => "double"
    case "blob" => "Blob"
    case _ => "int" // enum type
  }

  def isJdbcType(typeName: String): Boolean = typeName match {
    case "bool" => true
    case "short" => true
    case "byte" => true
    case "int" => true
    case "long" => true
    case "decimal" => true
    case "string" => true
    case "text" => true
    case "timestamp" => true
    case "float" => true
    case "double" => true
    case "blob" => true
    case _ => false
  }

  def toCqlType(typeName: String): String = typeName match {
    case "bool" => "bool"
    case "short" => "short"
    case "byte" => "byte"
    case "int" => "int"
    case "long" => "long"
    case "decimal" => "BigDecimal"
    case "string" => "String"
    case "text" => "String"
    case "timestamp" => "Timestamp"
    case "float" => "float"
    case "double" => "double"
    case "blob" => "Bytes"
    case _ => "int" // enum type
  }

  def toJavaType(typeName: String): String = typeName match {
    case "bool" => "boolean"
    case "short" => "short"
    case "byte" => "byte"
    case "int" => "int"
    case "long" => "long"
    case "decimal" => "BigDecimal"
    case "string" => "String"
    case "text" => "String"
    case "timestamp" => "Timestamp"
    case "float" => "float"
    case "double" => "double"
    case "blob" => "ByteString"
    case x =>
      cToPascal(x)
  }

  def toTypeScriptType(typeName: String): String = typeName match {
    case "bool" => "boolean"
    case "short" => "number"
    case "byte" => "number"
    case "int" => "number"
    case "identity" => "number"
    case "long" => "number"
    case "decimal" => "number"
    case "string" => typeName
    case "text" => "string"
    case "timestamp" => "Date"
    case "float" => "number"
    case "double" => "number"
    case "blob" => "number[]"
    case x =>
      cToPascal(x)
  }

  def isTypeScriptType(typeName: String): Boolean = typeName match {
    case "bool" => true
    case "short" => true
    case "byte" => true
    case "int" => true
    case "identity" => true
    case "long" => true
    case "decimal" => true
    case "string" => true
    case "text" => true
    case "timestamp" => true
    case "float" => true
    case "double" => true
    case "blob" => true
    case _ => false
  }

  def toProtobufType(typeName: String): String = typeName match {
    case "bool" => typeName
    case "short" => "int32"
    case "byte" => "bytes"
    case "int" => "int32"
    case "identity" => "int32"
    case "long" => "int64"
    case "decimal" => "double"
    case "string" => typeName
    case "text" => "string"
    case "timestamp" => "google.protobuf.Timestamp"
    case "float" => typeName
    case "double" => typeName
    case "blob" => "bytes"
    case x =>
      cToPascal(x)
  }

  def toModelTypeName(typeName: String): String = typeName match {
    case "bit" => "bool"
    case "smallint" => "short"
    case "tinyint" => "byte"
    case "int" => "int"
    case "int identity" => "int"
    case "bigint" => "long"
    case "decimal" => "decimal"
    case "varchar" => "string"
    case "nvarchar" => "string"
    case "text" => "string"
    case "datetime" => "timestamp"
    case "real" => "float"
    case "float" => "double"
    case "double" => "double"
    case "image" => "blob"
    case x =>
      pascalToC(x)
  }
}
