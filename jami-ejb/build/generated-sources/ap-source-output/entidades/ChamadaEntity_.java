package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ChamadaEntity.class)
public abstract class ChamadaEntity_ {

	public static volatile SingularAttribute<ChamadaEntity, Date> data_criacao_chamada;
	public static volatile SingularAttribute<ChamadaEntity, String> nome_chamada;
	public static volatile SingularAttribute<ChamadaEntity, Long> id_chamada;
	public static volatile SingularAttribute<ChamadaEntity, TurmaEntity> turma;

}

