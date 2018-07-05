package minhamesarpg

import grails.gorm.transactions.Transactional

@Transactional
class MesasService {

    def serviceConvertSaveJson(json,mesa) {
        int playersMax = Integer.parseInt(json.playersMax)
        int playersMin = Integer.parseInt(json.playersMin)
        int faixaEtaria = Integer.parseInt(json.faixaEtaria)
        mesa.tipoDoSistema = json.tipoDoSistema
        mesa.valueTiposDeMesas = json.valueTiposDeMesas
        mesa.faixaEtaria = faixaEtaria
        mesa.title = json.title
        mesa.mestre = json.mestre
        mesa.playersMin = playersMin
        mesa.playersMax = playersMax
        mesa.mapaPrincipal = json.mapaPrincipal
        mesa.descricao = json.descricao
        mesa.url = json.newFile.url
        mesa.size = json.size
        mesa.vagas = playersMax

        mesa.save(flush: true)
    }
}
