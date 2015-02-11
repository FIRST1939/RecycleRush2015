package org.usfirst.frc.team1939.robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class SpeedControllerSendable implements LiveWindowSendable{

	private ITable table;
	private final ITableListener listener = new ITableListener() {
        @Override
        public void valueChanged(ITable table, String key, Object value, boolean isNew) {
            if(key.equals("key")){
            	speed = table.getNumber("speed");
            	controller.set(speed);
            }
        }
    };
    
    private SpeedController controller;
    private double speed;
    
    public SpeedControllerSendable(SpeedController controller) {
		this.controller = controller;
		this.speed = 0;
	}

    @Override
    public void initTable(ITable table) {
        if(this.table!=null) this.table.removeTableListener(listener);
        this.table = table;
        if(table!=null) {
            table.putNumber("speed", speed);
            table.addTableListener(listener, false);
        }
    }

    @Override
    public ITable getTable() {
        return table;
    }

    @Override
    public void updateTable() {
    }

    @Override
    public void startLiveWindowMode() {
    	this.speed = 0;
    	controller.set(speed);
    }

    @Override
    public void stopLiveWindowMode() {
    	this.speed = 0;
    	controller.set(speed);
    }

	@Override
	public String getSmartDashboardType() {
		return null;
	}


}
