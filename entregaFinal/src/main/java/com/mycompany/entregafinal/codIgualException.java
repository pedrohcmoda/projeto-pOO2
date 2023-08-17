// Nome: Pedro Henrique Cunha Moda RA: 2476576

import java.util.ArrayList;
public class codIgualException extends Exception{
    Leitura l = Leitura.getLeitura();

	public ItemDeBiblioteca codigoIgual(ArrayList<ItemDeBiblioteca> itens, ItemDeBiblioteca p1){
		try{
			p1.setCodigo(l.entDados("\n Este codigo ja está sendo utilizado por outro item, digite outro: "), itens);
		}
		catch(codIgualException eci){
			p1 = eci.codigoIgual(itens,p1);
		}
		return p1;
	}
}
