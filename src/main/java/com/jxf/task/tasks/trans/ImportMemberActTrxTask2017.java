package com.jxf.task.tasks.trans;



import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.jxf.svc.sys.data.entity.SysDataImport;
import com.jxf.svc.sys.data.entity.SysDataTask;
import com.jxf.svc.sys.data.service.SysDataImportService;
import com.jxf.svc.sys.data.service.SysDataTaskService;
import com.jxf.transplantation.temp.member.ImportMemberActTrxUtils2017;


/**
 * 	账户流水
 *
 * @author wo
 */
@DisallowConcurrentExecution
public class ImportMemberActTrxTask2017 implements Job {

	@Autowired
	private SysDataImportService dataImportService;
	@Autowired
	private SysDataTaskService sysDataTaskService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

    	SysDataImport dataImport = dataImportService.getByHandler("memberActTrx2017");
    	SysDataTask task = new SysDataTask();
    	task.setData(dataImport);
    	task.setStatus(SysDataTask.Status.waitDo);
    	task.setIsOn(false); 
    	List<SysDataTask> taskList = sysDataTaskService.findList(task);
    	
    	for(SysDataTask dataTask:taskList) {

    		dataTask = sysDataTaskService.get(dataTask);
    		if(dataTask==null||dataTask.getStatus().equals(SysDataTask.Status.closed)) {
    			continue;
    		}
    		
    		dataTask.setStatus(SysDataTask.Status.doing);
    		dataTask.setIsOn(true);   		
    		sysDataTaskService.save(dataTask);
    		Long startId = dataTask.getStartId();
    		Long endId =dataTask.getEndId();
    		int quantity = ImportMemberActTrxUtils2017.importTrx(startId,endId);    
    		dataTask.setQuantity(quantity);
    		dataTask.setStatus(SysDataTask.Status.closed);
    		dataTask.setIsOn(false);
    		sysDataTaskService.save(dataTask);
    		dataImport.setImportQuantity(dataImport.getImportQuantity()+quantity);
    		dataImportService.save(dataImport);
    	}
   

    }
    

}