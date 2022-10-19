package com.bills.dr_stone.entities

class GenericError(
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
){

}

val ERROR_NAME_EMPTY: GenericError = GenericError(
    moduleName = "Bill",
    code = "EMPTY_NAME",
    description = "Name cannot be empty"
)

val DATABASE_ERROR: GenericError = GenericError(
    moduleName = "DATABASE",
    code = "DATABASE_ERROR",
    description = "An error occurs on database"
)