package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TurmaEntity.class)
public abstract class TurmaEntity_ {

	public static volatile SingularAttribute<TurmaEntity, String> nome_professor;
	public static volatile SingularAttribute<TurmaEntity, AtividadeEntity> atividade;
	public static volatile SingularAttribute<TurmaEntity, Long> id_turma;
	public static volatile SingularAttribute<TurmaEntity, Date> data_criacao;
	public static volatile ListAttribute<TurmaEntity, AlunoEntity> alunos;
	public static volatile SingularAttribute<TurmaEntity, ChamadaEntity> chamada;
	public static volatile SingularAttribute<TurmaEntity, String> nome_turma;
	public static volatile SingularAttribute<TurmaEntity, String> turno;

}

