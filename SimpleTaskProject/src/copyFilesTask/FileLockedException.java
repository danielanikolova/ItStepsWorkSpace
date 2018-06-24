package copyFilesTask;

public class FileLockedException extends Exception {

	public FileLockedException() {
		super("The current file is locked by another process.");
	}
}
