package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SocialEntity.class)
public abstract class SocialEntity_ {

	public static volatile SingularAttribute<SocialEntity, PessoaEntity> pessoa;
	public static volatile SingularAttribute<SocialEntity, Integer> numero_casa;
	public static volatile SingularAttribute<SocialEntity, String> profissao_social;
	public static volatile SingularAttribute<SocialEntity, EnderecoEntity> rua_social;
	public static volatile SingularAttribute<SocialEntity, String> nro_joves;
	public static volatile SingularAttribute<SocialEntity, String> obs_gestante;
	public static volatile SingularAttribute<SocialEntity, String> grau_instrucao;
	public static volatile SingularAttribute<SocialEntity, String> auxilio_governo;
	public static volatile SingularAttribute<SocialEntity, String> renda_mensal_familiar;
	public static volatile SingularAttribute<SocialEntity, String> obs_acamado;
	public static volatile SingularAttribute<SocialEntity, String> complemento_rua;
	public static volatile SingularAttribute<SocialEntity, String> obs_deficiente;
	public static volatile SingularAttribute<SocialEntity, Long> id;
	public static volatile SingularAttribute<SocialEntity, String> fone_social;
	public static volatile SingularAttribute<SocialEntity, String> situcao_social;
	public static volatile SingularAttribute<SocialEntity, String> gestante;
	public static volatile SingularAttribute<SocialEntity, String> acamado;
	public static volatile SingularAttribute<SocialEntity, String> nro_bebes;
	public static volatile SingularAttribute<SocialEntity, String> nro_adultos;
	public static volatile SingularAttribute<SocialEntity, Date> data_cadastro;
	public static volatile SingularAttribute<SocialEntity, String> nro_adolecentes;
	public static volatile SingularAttribute<SocialEntity, String> deficiente;
	public static volatile SingularAttribute<SocialEntity, String> nro_criancas;
	public static volatile SingularAttribute<SocialEntity, String> rg_social;
	public static volatile ListAttribute<SocialEntity, BeneficioConcedidoEntity> beneficios;

}

