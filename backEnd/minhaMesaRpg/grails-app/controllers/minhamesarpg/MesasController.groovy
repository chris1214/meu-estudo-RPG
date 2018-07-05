package minhamesarpg
import grails.converters.JSON
import org.grails.web.json.JSONObject

class MesasController {

    static responseFormats = ['json']
    static allowedMethods = [
            list: "GET",
            save: "POST",
            update: "PUT"
    ]

    Mesas mesa = new Mesas()
    JSONObject json = request.JSON
    def mesasService

    def list() {
        respond Mesas.list()
    }
    def save() {
        mesasService.serviceConvertSaveJson(json,mesa)
        respond json
    }
    def update (){
        mesasService.serviceConvertSaveJson(json,mesa)
        respond json
    }
}
