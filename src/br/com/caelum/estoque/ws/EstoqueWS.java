package br.com.caelum.estoque.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;

import br.com.caelum.estoque.modelo.item.*;


@WebService
public class EstoqueWS {

    private ItemDao dao = new ItemDao();

    @WebMethod(operationName="todosOsItens")
    @ResponseWrapper(localName="itens")
    @WebResult(name="item")
    @RequestWrapper(localName="listaItens")
    public List<Item> getItens(@WebParam(name = "filtros") Filtros filtros) {

        List<Filtro> lista = filtros.getLista();
        List<Item> result = dao.todosItens(lista);

        return result;
    }
}
