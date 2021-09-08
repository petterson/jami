package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProgramaEntity.class)
public abstract class ProgramaEntity_ {

	public static volatile SingularAttribute<ProgramaEntity, Date> data_criacao;
	public static volatile SingularAttribute<ProgramaEntity, String> nome_programa;
	public static volatile SingularAttribute<ProgramaEntity, Long> id;
	public static volatile SingularAttribute<ProgramaEntity, String> descricao_programa;
	public static volatile ListAttribute<ProgramaEntity, ProjetoEntity> nome_projetos;

}

