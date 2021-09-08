package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CartorioEntity.class)
public abstract class CartorioEntity_ {

	public static volatile SingularAttribute<CartorioEntity, EnderecoEntity> rua_cartorio;
	public static volatile SingularAttribute<CartorioEntity, Integer> numero_caetorio;
	public static volatile ListAttribute<CartorioEntity, AlunoEntity> list_aluno;
	public static volatile SingularAttribute<CartorioEntity, Long> id;
	public static volatile SingularAttribute<CartorioEntity, String> nome_cartorio;

}

