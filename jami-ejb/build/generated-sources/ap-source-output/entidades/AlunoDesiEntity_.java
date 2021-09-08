package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AlunoDesiEntity.class)
public abstract class AlunoDesiEntity_ {

	public static volatile SingularAttribute<AlunoDesiEntity, String> situacao;
	public static volatile SingularAttribute<AlunoDesiEntity, String> escola;
	public static volatile SingularAttribute<AlunoDesiEntity, String> nome;
	public static volatile SingularAttribute<AlunoDesiEntity, Long> id;
	public static volatile ListAttribute<AlunoDesiEntity, String> nome_turmas;
	public static volatile SingularAttribute<AlunoDesiEntity, String> sexo;
	public static volatile SingularAttribute<AlunoDesiEntity, Date> data_desistencia;
	public static volatile ListAttribute<AlunoDesiEntity, String> nome_atividades;

}

