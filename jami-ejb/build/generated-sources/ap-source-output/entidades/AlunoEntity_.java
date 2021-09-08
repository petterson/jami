package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AlunoEntity.class)
public abstract class AlunoEntity_ {

	public static volatile SingularAttribute<AlunoEntity, String> fone_aluno;
	public static volatile SingularAttribute<AlunoEntity, String> bolsa_familia;
	public static volatile SingularAttribute<AlunoEntity, String> situacao;
	public static volatile SingularAttribute<AlunoEntity, PessoaEntity> pessoa;
	public static volatile SingularAttribute<AlunoEntity, Integer> matricula_escola;
	public static volatile SingularAttribute<AlunoEntity, String> cor_raca;
	public static volatile SingularAttribute<AlunoEntity, Integer> numero_casa;
	public static volatile ListAttribute<AlunoEntity, IrmaoEntity> irmaos;
	public static volatile SingularAttribute<AlunoEntity, String> turno;
	public static volatile SingularAttribute<AlunoEntity, MaeEntity> mae;
	public static volatile SingularAttribute<AlunoEntity, EscolaEntity> escola;
	public static volatile SingularAttribute<AlunoEntity, String> ponto_referencia;
	public static volatile SingularAttribute<AlunoEntity, Integer> folha_certidao;
	public static volatile SingularAttribute<AlunoEntity, CartorioEntity> cartorio;
	public static volatile SingularAttribute<AlunoEntity, PaiEntity> pai;
	public static volatile SingularAttribute<AlunoEntity, Long> id;
	public static volatile SingularAttribute<AlunoEntity, Date> data_matricula;
	public static volatile ListAttribute<AlunoEntity, ProblemaSaudeEntity> problema_saude;
	public static volatile SingularAttribute<AlunoEntity, String> orgao_expedidor;
	public static volatile ListAttribute<AlunoEntity, AlergiaEntity> alergia;
	public static volatile SingularAttribute<AlunoEntity, Date> data_expedicao;
	public static volatile SingularAttribute<AlunoEntity, Integer> numero_certidao;
	public static volatile SingularAttribute<AlunoEntity, EnderecoEntity> rua_aluno;
	public static volatile SingularAttribute<AlunoEntity, String> rg;
	public static volatile SingularAttribute<AlunoEntity, CidadeEntity> cidade_nascimento;
	public static volatile SingularAttribute<AlunoEntity, String> serie;
	public static volatile SingularAttribute<AlunoEntity, String> sexo;
	public static volatile SingularAttribute<AlunoEntity, Integer> livro_certidao;

}

