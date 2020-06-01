package br.com.caelum.estoque.ws;

import javax.jws.WebService;
import java.util.List;

import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;


@WebService
public class EstoqueWS {

    private ItemDao dao = new ItemDao();

    public List<Item> getItens() {

        System.out.println("Chamando gatItens()");
        List<Item> lista = dao.todosItens();
        return lista;
    }
}
