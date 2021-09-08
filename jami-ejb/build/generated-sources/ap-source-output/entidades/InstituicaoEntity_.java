package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InstituicaoEntity.class)
public abstract class InstituicaoEntity_ {

	public static volatile SingularAttribute<InstituicaoEntity, String> fone_instiuicao;
	public static volatile SingularAttribute<InstituicaoEntity, TipoInstituicaoEntity> tipoInstituicao;
	public static volatile SingularAttribute<InstituicaoEntity, String> nome_instituicao;
	public static volatile SingularAttribute<InstituicaoEntity, Long> id;
	public static volatile SingularAttribute<InstituicaoEntity, String> cnpj;
	public static volatile SingularAttribute<InstituicaoEntity, String> email_instituicao;
	public static volatile SingularAttribute<InstituicaoEntity, Date> data_parceria;

}

