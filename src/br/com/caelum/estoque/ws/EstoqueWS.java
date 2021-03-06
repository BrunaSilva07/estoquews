package br.com.caelum.estoque.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;

import br.com.caelum.estoque.modelo.item.*;
import br.com.caelum.estoque.modelo.usuario.AutorizacaoException;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;


@WebService
public class EstoqueWS {

    private ItemDao dao = new ItemDao();

    @WebMethod(operationName = "todosOsItens")
    @ResponseWrapper(localName = "itens")
    @WebResult(name = "item")
    @RequestWrapper(localName = "listaItens")
    public List<Item> getItens(@WebParam(name = "filtros") Filtros filtros) {

        List<Filtro> lista = filtros.getLista();
        List<Item> result = dao.todosItens(lista);

        return result;
    }

    @WebMethod(operationName = "cadastrarItens")
    @WebResult(name = "item")
    public Item cadastrarItem(
            @WebParam(name = "tokenUsuario", header = true) TokenUsuario token,
            @WebParam(name = "item") Item item)
            throws AutorizacaoException {

        System.out.println("Cadastrando item " + item + ", Token: " + token);
        if (!new TokenDao().ehValido(token)) {
            throw new AutorizacaoException("Autorizacao falhou");
        }

        //novo
        new ItemValidador(item).validate();

        this.dao.cadastrar(item);
        return item;
    }
}
