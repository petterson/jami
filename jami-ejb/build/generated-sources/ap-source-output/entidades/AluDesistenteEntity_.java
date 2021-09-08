package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AluDesistenteEntity.class)
public abstract class AluDesistenteEntity_ {

	public static volatile SingularAttribute<AluDesistenteEntity, String> situacao;
	public static volatile SingularAttribute<AluDesistenteEntity, String> escola;
	public static volatile ListAttribute<AluDesistenteEntity, String> list_atividades;
	public static volatile ListAttribute<AluDesistenteEntity, String> list_turma;
	public static volatile SingularAttribute<AluDesistenteEntity, String> nome;
	public static volatile SingularAttribute<AluDesistenteEntity, Long> id;
	public static volatile SingularAttribute<AluDesistenteEntity, String> sexo;
	public static volatile SingularAttribute<AluDesistenteEntity, Date> data_matricula;

}

