
INSERT_RESOURCE_TYPES_STMT = INSERT into obs_resourcetype(resourcetype_name) values(?);

UPDATE_RESOURCE_TYPES_STMT = UPDATE obs_resourcetype set resourcetype_name=? where resourcetype_id=?;

DELETE_RESOURCE_TYPES_STMT = DELETE FROM obs_resourcetype where resourcetype_id=?

GET_RESOURCE_TYPES_STMT = select * from obs_resourcetype;

GET_RESOURCE_TYPES_BY_ID_STMT=select * from obs_resourcetype where id=?
