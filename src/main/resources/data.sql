DROP TABLE machines IF EXISTS;
CREATE TABLE machines(id SERIAL, supplierId INTEGER, cpu_current INTEGER, cpu_max INTEGER,
                        gpu_current INTEGER, gpu_max INTEGER ,
                        memory_current INTEGER, memory_max INTEGER,
                        local_storage_current INTEGER, local_storage_max INTEGER);
INSERT INTO machines(supplierId, cpu_current, cpu_max, gpu_current, gpu_max, memory_current, memory_max, local_storage_current, local_storage_max) VALUES (123,2,4,1,2,4,32,256,1024)