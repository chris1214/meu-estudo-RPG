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
    def list() {
        respond Mesas.list()
    }
    def save() {
        Mesas mesa = new Mesas()
        JSONObject json = request.JSON

        mesa.tipoDoSistema = json.tipoDoSistema
        mesa.valueTiposDeMesas = json.valueTiposDeMesas
        mesa.faixaEtaria = json.faixaEtaria
        mesa.title = json.title
        mesa.mestre = json.mestre
        mesa.playersMin = json.playersMin
        mesa.playersMax = json.playersMax
        mesa.mapaPrincipal = json.mapaPrincipal
        mesa.descricao = json.descricao
        mesa.type = json.newFile.type
        mesa.url = json.newFile.url
        mesa.size = json.newFile.size


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

        mesa.save(flush: true)
        if (!mesa.save()) {
            mesa.errors.allErrors.each {
                println it
            }
        }
        respond json

    }
    def fake(){

        println '----------------------' *50
        println params.upload
        println params
        println '----------------------' *50

    }
}
