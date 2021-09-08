package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AtividadeEntity.class)
public abstract class AtividadeEntity_ {

	public static volatile SingularAttribute<AtividadeEntity, Date> data_criacao;
	public static volatile SingularAttribute<AtividadeEntity, String> nome_atividade;
	public static volatile SingularAttribute<AtividadeEntity, ProjetoEntity> projeto;
	public static volatile SingularAttribute<AtividadeEntity, Long> id;
	public static volatile ListAttribute<AtividadeEntity, TurmaEntity> nome_turmas;

}

