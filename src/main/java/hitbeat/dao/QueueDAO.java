package hitbeat.dao;

import hitbeat.model.Queue;

public class QueueDAO extends BaseDAO<Queue>{

    public QueueDAO() {
        super(Queue.class);
    }

    @Override
    protected void updateProperties(Queue existingEntity, Queue newEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProperties'");
    }
    
}
