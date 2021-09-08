package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DistritoEntity.class)
public abstract class DistritoEntity_ {

	public static volatile SingularAttribute<DistritoEntity, CidadeEntity> cidade;
	public static volatile ListAttribute<DistritoEntity, EnderecoEntity> list_endereco;
	public static volatile SingularAttribute<DistritoEntity, String> nome_distrito;
	public static volatile SingularAttribute<DistritoEntity, Long> id;

}

