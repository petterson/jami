package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FuncionarioEntity.class)
public abstract class FuncionarioEntity_ {

	public static volatile SingularAttribute<FuncionarioEntity, String> fone_funcionario;
	public static volatile SingularAttribute<FuncionarioEntity, String> clt_funcionario;
	public static volatile SingularAttribute<FuncionarioEntity, PessoaEntity> pessoa;
	public static volatile SingularAttribute<FuncionarioEntity, String> rg;
	public static volatile SingularAttribute<FuncionarioEntity, Integer> numero_casa;
	public static volatile SingularAttribute<FuncionarioEntity, String> pis_funcionario;
	public static volatile SingularAttribute<FuncionarioEntity, DepartamentoEntity> departamento;
	public static volatile SingularAttribute<FuncionarioEntity, Long> id;
	public static volatile SingularAttribute<FuncionarioEntity, CargoEntity> cargo_funcionario;
	public static volatile SingularAttribute<FuncionarioEntity, Date> data_contratacao;
	public static volatile SingularAttribute<FuncionarioEntity, EnderecoEntity> rua_funcionario;

}

