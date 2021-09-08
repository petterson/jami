package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaeEntity.class)
public abstract class MaeEntity_ {

	public static volatile SingularAttribute<MaeEntity, EnderecoEntity> rua_mae;
	public static volatile SingularAttribute<MaeEntity, String> escolaridade_mae;
	public static volatile SingularAttribute<MaeEntity, PessoaEntity> pessoa;
	public static volatile SingularAttribute<MaeEntity, String> rg_mae;
	public static volatile SingularAttribute<MaeEntity, String> local_trabalho_mae;
	public static volatile ListAttribute<MaeEntity, AlunoEntity> list_aluno;
	public static volatile SingularAttribute<MaeEntity, Integer> numero_casa_mae;
	public static volatile SingularAttribute<MaeEntity, Long> id;
	public static volatile SingularAttribute<MaeEntity, String> fone_mae;
	public static volatile SingularAttribute<MaeEntity, String> profissao_mae;

}

