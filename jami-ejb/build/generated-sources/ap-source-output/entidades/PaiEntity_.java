package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PaiEntity.class)
public abstract class PaiEntity_ {

	public static volatile SingularAttribute<PaiEntity, PessoaEntity> pessoa;
	public static volatile SingularAttribute<PaiEntity, Integer> numero_casa_pai;
	public static volatile SingularAttribute<PaiEntity, String> fone_pai;
	public static volatile SingularAttribute<PaiEntity, EnderecoEntity> rua_pai;
	public static volatile ListAttribute<PaiEntity, AlunoEntity> list_aluno;
	public static volatile SingularAttribute<PaiEntity, String> profissao_pai;
	public static volatile SingularAttribute<PaiEntity, String> escolaridade_pai;
	public static volatile SingularAttribute<PaiEntity, Long> id;
	public static volatile SingularAttribute<PaiEntity, String> rg_pai;
	public static volatile SingularAttribute<PaiEntity, String> local_trabalho_pai;

}

