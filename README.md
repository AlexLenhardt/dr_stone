###Cadastro de despesas

	Cadastro:
        Cadastrar uma despesa com:
            Código(opcional)
            Título
            Valor
            Data de vencimento
            Recorrente

	Edição:
		Permitir edição quando:
			Não tenha passado do vencimento
		Permitir edição dos seguintes campos
			Título
			Valor
			Vencimento
			Recorrente


	Leituras:
		Listagem:
			Listar despesas ordernado por dia do mês
			Pode fazer a listagem do mês(trará o total gasto)
		Get:
			É permitido fazer a consulta por:
				Código
				Data de vencimento(trará uma listagem com as despesas caso tenha mais de uma naquele dia)

		Pode ser feita a solicitação total de despesas e receitas
			Lucro
			Prejuizos

	Delete:
		É possível deletar caso não tenha passado o vencimento da conta.



###Cadastro de receita

    Cadastro:
    Cadastrar receita com:
        Título
        Valor
        Data do recebimento
        Recorrente

	Edição:
		Pode editar sempre

	Deletar:
		Pode deletar sempre