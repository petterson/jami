package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProblemaSaudeEntity.class)
public abstract class ProblemaSaudeEntity_ {

	public static volatile SingularAttribute<ProblemaSaudeEntity, String> problema_saude;
	public static volatile SingularAttribute<ProblemaSaudeEntity, String> grau_perigo;
	public static volatile ListAttribute<ProblemaSaudeEntity, AlunoEntity> list_aluno;
	public static volatile SingularAttribute<ProblemaSaudeEntity, Long> id;

}

