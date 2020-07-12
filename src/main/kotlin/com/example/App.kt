package com.example

import com.github.snabbdom.VNode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import org.w3c.dom.get
import pl.treksoft.jquery.JQueryStatic
import pl.treksoft.jquery.jQuery
import pl.treksoft.kvision.Application
import pl.treksoft.kvision.html.Div
import pl.treksoft.kvision.html.div
import pl.treksoft.kvision.html.span
import pl.treksoft.kvision.panel.ContainerType
import pl.treksoft.kvision.panel.root
import pl.treksoft.kvision.startApplication
import pl.treksoft.kvision.require
import kotlin.browser.document
import kotlin.browser.window

fun runBlocking(block: suspend (scope : CoroutineScope) -> Unit): dynamic = GlobalScope.promise { block(this) }

class App : Application() {

    init {
        require("css/kvapp.css")
    }

    override fun start() {
        root("body", containerType = ContainerType.NONE, addRow = false) {
            add(Map())
        }
    }
}

external val THREE: dynamic = definedExternally


class Map : Div() {
    init {
        id = "edmap"
    }

    override fun afterInsert(node: VNode) {
//        val elem = getElementJQuery()!!
        jQuery(document).ready {
            println(THREE.ShapePath)
            loadMap()
        }
    }
}

fun loadMap() {
    eval("""
                            var systemsData = {
   "categories":{
      "Site Types":{
         "100":{
            "name":"Guardian Ruin Location",
            "color":"ff0000"
         },
         "101":{
            "name":"POI",
            "color":"0000ff"
         },
         "400":{
            "name":"MegaShip",
            "color":"ffff00"
         },
         "1000":{
            "name":"Inactive",
            "color":"ffffff"
         },
         "1001":{
            "name":"Active",
            "color":"ff8565"
         }
      }
   },
   "systems":[
      {
         "name":"HIP 22460",
         "coords":{
            "x":"-41.3125",
            "y":"-58.96875",
            "z":"-354.78125"
         },
         "cat":[
            "101"
         ]
      },
      {
         "name":"MEROPE",
         "coords":{
            "x":"-78.59375",
            "y":"-149.625",
            "z":"-340.53125"
         },
         "cat":[
            "101"
         ]
      },
      {
         "name":"Col 173 Sector LJ-F C12-0 (The Cete)",
         "coords":{
            "x":"1202.125",
            "y":"-213.40625",
            "z":"-165.5625"
         },
         "cat":[
            "400"
         ]
        },                     
    ]
}

    Ed3d.basePath = './map/'
    Ed3d.init({
        container   : 'edmap',
        withHudPanel : true,
        hudMultipleSelect : true,
        effectScaleSystem : [10,1600],
        playerPos: [-117,-65,-330],
        startAnim: true,
        showGalaxyInfos: true,
        popupDetail : true,
        withFullscreenToggle : true,
        json    : systemsData,
        starSprite: "textures/lensflare/star_round.png"

  });

                """.trimIndent())

}

fun main() {
    startApplication(::App)
}
