using BusShuttleManager.Services;

namespace BusShuttleManager;


public class Program
{
    public static void Main(string[] args)
    {
        var builder = WebApplication.CreateBuilder(args);

        ConfigureServices(builder.Services); 

        var app = builder.Build();


        if(!app.Environment.IsDevelopment())
        {
            app.UseExceptionHandler("/Home/Error");
            app.UseHsts();
        }
        
        app.UseHttpsRedirection();
        app.UseStaticFiles();
        app.UseRouting();

        app.UseEndpoints(endpoints =>
        {
            endpoints.MapControllers();
            endpoints.MapDefaultControllerRoute();
        });

        app.Run();
    }

    private static void ConfigureServices(IServiceCollection services)
    {

        services.AddControllersWithViews();

        services.AddScoped<IDriverService, DriverServices>();
        services.AddScoped<IBusService, BusServices>();
        services.AddScoped<IRouteService, RouteServices>();
        services.AddScoped<IStopService, StopServices>();
        services.AddScoped<IEntryService, EntryServices>();
        services.AddScoped<ILoopService, LoopServices>();
    }

}
