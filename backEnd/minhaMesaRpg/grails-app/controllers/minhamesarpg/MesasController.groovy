package minhamesarpg
import grails.converters.JSON
import org.grails.web.json.JSONObject

class MesasController {

    static responseFormats = ['json']
    static allowedMethods = [
            list: "GET",
            save: "POST",
            fake: "POST"
    ]
    def mesasService
    def list() {
        respond Mesas.list()
    }
    def save() {
        Mesas mesa = new Mesas()
        JSONObject json = request.JSON

        mesasService.serviceConvertSaveJson(json,mesa)
        respond json

    }
}
