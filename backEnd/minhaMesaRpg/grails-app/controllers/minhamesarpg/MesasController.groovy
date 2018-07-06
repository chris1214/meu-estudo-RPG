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

    Mesas newMesa = new Mesas()
    def mesasService

    def list() {
        def getMesa = Mesas.get(params.id)
        respond getMesa
    }
    def save() {
        JSONObject json = request.JSON
        mesasService.serviceConvertSaveJson(json,newMesa)
        respond json
    }
    def update (){
        def getMesa = Mesas.get(params.id)
        getMesa.vagas = getMesa.vagas - 1
        getMesa.save(flush: true)
        respond getMesa
    }
}
