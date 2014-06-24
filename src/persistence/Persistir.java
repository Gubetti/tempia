package persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Motor;

public class Persistir {

	public static boolean abrir(File file) {
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new FileInputStream(file));
			Motor motor = (Motor) in.readObject();
			in.close();
			Motor.getInstancia().setRegras(motor.getRegras());
			Motor.getInstancia().setVariaveis(motor.getVariaveis());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public static boolean salvar(File file) {
        try {
        	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(Motor.getInstancia());
			out.flush();
		    out.close();
		} catch (IOException e) {
			return false;
		}
        
        return true;
	}
	
}
