package com.bills.dr_stone.entities

abstract class GenericError(
    /**
     * O nome do módulo que definiu a especificação real desse erro.
     */
    val moduleName: String,
    /**
     * Um código representando esse erro.
     *
     * Obs.: Seguir os mesmos padrões de nomeclatura de constantes: CAIXA_ALTA_SEPARADO_POR_UNDERLINE
     */
    val code: String,
    /**
     * A descrição desse erro.
     */
    val description: String,
)