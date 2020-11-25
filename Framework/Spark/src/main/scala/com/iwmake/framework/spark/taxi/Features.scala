package com.iwmake.framework.spark.taxi

import com.esri.core.geometry.{Geometry, GeometryEngine}
import org.json4s.JsonAST.JObject
import org.json4s.NoTypeHints
import org.json4s.jackson.Serialization


case class FeatureCollection(features: List[Feature])

case class Feature(properties: Map[String, String], geometry: JObject) {
  def getGeometry(): Geometry = {
    import org.json4s.jackson.JsonMethods._
    val mapGeo = GeometryEngine.geoJsonToGeometry(compact(render(geometry)), 0, Geometry.Type.Unknown)
    mapGeo.getGeometry
  }
}

object FeatureExtraction {
  def parseJson(json: String): FeatureCollection = {
    import org.json4s.jackson.Serialization.read
    // 1. 导入一个formats 隐式转换
    implicit val formats = Serialization.formats(NoTypeHints)
    // 2. JSON-->Obj
    val featureCollection = read[FeatureCollection](json)
    featureCollection
  }
}