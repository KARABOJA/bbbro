package org.eclipse.om2m.persistence.eclipselink.internal.dao;

import java.util.List;

import org.eclipse.om2m.commons.entities.AeAnncEntity;
import org.eclipse.om2m.commons.entities.FlexContainerAnncEntity;
import org.eclipse.om2m.commons.entities.LabelEntity;
import org.eclipse.om2m.persistence.eclipselink.internal.DBTransactionJPAImpl;
import org.eclipse.om2m.persistence.service.DBTransaction;

public class FlexContainerAnncDAO extends AbstractDAO<FlexContainerAnncEntity> {

	@Override
	public FlexContainerAnncEntity find(DBTransaction dbTransaction, Object id) {
		DBTransactionJPAImpl transaction = (DBTransactionJPAImpl) dbTransaction;
		return transaction.getEm().find(FlexContainerAnncEntity.class, id);
	}

	@Override
	public void delete(DBTransaction dbTransaction, FlexContainerAnncEntity resource) {
		DBTransactionJPAImpl transaction = (DBTransactionJPAImpl) dbTransaction;

		// de-associate labels
		List<LabelEntity> labels = resource.getLabelsEntities();
		for (LabelEntity label : labels) {
			label.getLinkedFcnt().remove(resource);
		}

		transaction.getEm().remove(resource);
		transaction.getEm().getEntityManagerFactory().getCache().evict(AeAnncEntity.class);
		transaction.getEm().getEntityManagerFactory().getCache().evict(FlexContainerAnncEntity.class);
		transaction.getEm().getEntityManagerFactory().getCache().evict(LabelEntity.class);

	}

}
