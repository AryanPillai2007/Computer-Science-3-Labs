import java.util.LinkedList;
import java.util.Queue;

public class Melody {
    private Queue<Note> notes;

    public Melody(Queue<Note> song) {
        this.notes = new LinkedList<>();
        while (!song.isEmpty()) {
            this.notes.add(song.poll());
        }
    }
    public double getTotalDuration() {
        double totalDuration = 0.0;
        Queue<Note> tempQueue = new LinkedList<>(this.notes); 
        while (!tempQueue.isEmpty()) {
            Note note= tempQueue.poll();
            totalDuration+= note.getDuration();
            if (note.isRepeat()) {
                while (!tempQueue.isEmpty()) {
                    Note nextNote = tempQueue.poll();
                    totalDuration += nextNote.getDuration();
                    if (nextNote.isRepeat()) {
                        break; 
                    }
                }
            }
        }
        return totalDuration;
    }  
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Queue<Note> tempQueue = new LinkedList<>(this.notes);
        while (!tempQueue.isEmpty()) {
            Note note = tempQueue.poll();
            builder.append(note.toString()).append("\n");
        }
        return builder.toString();
    }
    public void changeTempo(double tempo) {
        Queue<Note> tempQueue = new LinkedList<>(this.notes);
        while (!tempQueue.isEmpty()) {
            Note note = tempQueue.poll();
        double newDuration = note.getDuration() 
            		*tempo; 
            note.setDuration(newDuration);
            this.notes.add(note);
        }
    }
    public void reverse() {
    	Queue<Note> tempQueue = new LinkedList<>();
    	while (!this.notes.isEmpty()) {
    		tempQueue.add(this.notes.poll());
    		} while (!tempQueue.isEmpty()) {
    			this.notes.add(tempQueue.poll()); 
    	}
    }
    public void append(Melody other) {
        Queue<Note> otherNotes = other.getNotes();
        while (!otherNotes.isEmpty()) {
            this.notes.add(otherNotes.poll());
        }
    }
    public void play() {
        Queue<Note> tempQueue = new LinkedList<>(this.notes);
        Queue<Note> repeatQueue = new LinkedList<>();

        while (!tempQueue.isEmpty()) {
            Note note = tempQueue.poll();
            if (note.isRepeat()) {
                if (repeatQueue.isEmpty()) {
                    repeatQueue.add(note); 
                }
                else
                {
                    for (Note repeatNote:repeatQueue) {
                        repeatNote.play();
                    }
                    repeatQueue.clear(); 
                    continue;
                }
            }
            note.play(); 
            if (!note.isRepeat()) {
                repeatQueue.add(note); 
                }
        }
        for (Note repeatNote: repeatQueue) {
            repeatNote.play();
        }
    }

    public Queue<Note> getNotes() {
        return this.notes;
    }
}
