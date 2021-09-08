package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProjetoEntity.class)
public abstract class ProjetoEntity_ {

	public static volatile SingularAttribute<ProjetoEntity, Date> data_inicio;
	public static volatile ListAttribute<ProjetoEntity, InstituicaoEntity> instituicoes;
	public static volatile SingularAttribute<ProjetoEntity, ProgramaEntity> programa;
	public static volatile SingularAttribute<ProjetoEntity, String> cust_anual;
	public static volatile SingularAttribute<ProjetoEntity, Long> id;
	public static volatile SingularAttribute<ProjetoEntity, String> nome_projeto;
	public static volatile ListAttribute<ProjetoEntity, AtividadeEntity> nome_atividades;

}

