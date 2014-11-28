package com.agileengineers.com.blog.tdddomainexpert.importer.support.verify;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.agileengineers.com.blog.tdddomainexpert.importer.model.Issue;
import com.agileengineers.com.blog.tdddomainexpert.importer.model.ModelRepository;

public class ModelRepositoryVerifier {

	private ModelRepository repository = mock(ModelRepository.class);
	private List<Issue> storedIssueList = new ArrayList<>();

	public ModelRepositoryVerifier() {
		trainRepositroyItCollectsStoredIssues();
	}

	public SingleIssueVerifier
	storedIssue() {
		return new SingleIssueVerifier(storedIssueList);
	}

	public ListVerifier<Issue>
	storedIssues() {
		return new ListVerifier<Issue>(storedIssueList);
	}

	public ModelRepository
	modelRepository() {
		return repository;
	}

	private void
	trainRepositroyItCollectsStoredIssues() {

		doAnswer(new Answer<Void>() {

			@Override public Void
			answer(InvocationOnMock invocation) throws Throwable {
				Issue issueToStore = (Issue) invocation.getArguments()[0];
				storedIssueList.add(issueToStore);
				return null;
			}
		}).when(repository).storeIssue(Mockito.any(Issue.class));

	}
}