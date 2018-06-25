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



       println '----------------------' *50
        println mesa.tipoDoSistema
        println mesa.valueTiposDeMesas
        println mesa.faixaEtaria
        println mesa.title
        println mesa.mestre
        println mesa.playersMin
        println mesa.playersMax
        println mesa.mapaPrincipal
        println mesa.descricao
        println mesa.type
        println mesa.url
        println mesa.size
        println '----------------------' * 50
        respond json

    }
}
