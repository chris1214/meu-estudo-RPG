package minhamesarpg

import grails.gorm.transactions.Transactional

@Transactional
class MesasService {

    def serviceConvertSaveJson(json,mesa) {
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
    }
}
