using DomainModel;
namespace BusShuttleManager.Services
{
    public class LoopServices : ILoopService
    {
        private readonly DataContext db = new DataContext();
        List<Loop> loops;

        public List<Loop> getAllLoops()
        {
            loops = db.Loop
                .Select(l => new Loop(l.Id, l.Name)).ToList();
            return loops;
        }

        public Loop findLoopById(int id)
        {
            var loop = db.Loop
                .SingleOrDefault(loop => loop.Id == id);

            return new Loop(loop);
        }

        public void UpdateLoopById(int id, string name)
        {
            var existingLoop = db.Loop.SingleOrDefault(loop => loop.Id == id);
            existingLoop.Update(name);

            var loop = db.Loop
                .SingleOrDefault(loop => loop.Id == existingLoop.Id);
            
            if(loop != null)
            {
                loop.Name = name;
                db.SaveChanges();
            }
        }

        public void CreateNewLoop(string name)
        {
            var totalLoops = db.Loop.Count();
            db.Add(new Loop{Id=totalLoops+1, Name=name});
            db.SaveChanges();
        }
    }
}