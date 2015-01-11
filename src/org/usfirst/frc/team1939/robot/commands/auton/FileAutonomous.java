package org.usfirst.frc.team1939.robot.commands.auton;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class FileAutonomous extends CommandGroup {

	public FileAutonomous(String path) throws Exception {
		Path input = Paths.get(path);
		if (!Files.exists(input)) {
			throw new FileNotFoundException();
		} else {
			try {
				BufferedReader reader = Files.newBufferedReader(input);

				// Read Lines
				List<String> lines = new ArrayList<String>();
				while (reader.ready()) {
					lines.add(reader.readLine());
				}

				// Process file
				this.process(lines);

				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void process(List<String> lines) throws Exception {
		for (String line : lines) {
			String[] parts = line.split(" ");
			boolean parallel = false;

			// Pull name out
			String name = parts[0];
			int argsNum = parts.length - 1;
			if (name.equalsIgnoreCase("para")) {
				parallel = true;
				name = parts[1];
				argsNum = parts.length - 2;
			}

			// Pull arguments out
			String[] args = new String[argsNum];
			int startIndex = parts.length - args.length;
			for (int i = 0; i < args.length; i++) {
				args[i] = parts[startIndex + i];
			}

			// Match the command
			Command cmd = this.matchCommand(name, args);

			// Add command to CommandGroup
			if (parallel) {
				this.addParallel(cmd);
			} else {
				this.addSequential(cmd);
			}
		}
	}

	public Command matchCommand(String name, String[] args) throws Exception {
		switch (name) {
		default:
			throw new Exception("Unrecognized Command: " + name);
		}
	}
}
