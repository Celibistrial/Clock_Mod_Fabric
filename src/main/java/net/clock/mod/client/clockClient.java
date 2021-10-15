package net.clock.mod.client;

import net.clock.mod.JsonFile;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.json.simple.JSONObject;

import java.io.*;


import net.minecraft.util.Formatting;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
// word()
// literal("foo")
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.literal;

// Import everything

public class clockClient implements ClientModInitializer {
    public static int x;
    public static int y;
    public static int colour;
    private JsonFile jsonFile = new JsonFile();
    @Override
    public void onInitializeClient() {
        //  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            x = jsonFile.read()[0];
            y = jsonFile.read()[1];
            colour = jsonFile.read()[2];



            ClientCommandManager.DISPATCHER.register(literal("pos").then(ClientCommandManager.argument("x", integer(0, 10000)).then(ClientCommandManager.argument("y", integer(0, 10000)).executes(context -> {
                x = context.getArgument("x",Integer.class);
                y = context.getArgument("y",Integer.class);

                JSONObject object = new JSONObject();
                jsonFile.write(x,y,colour);
                return 1;


            }))));
            ClientCommandManager.DISPATCHER.register(literal("colours").executes(ctx -> {

                Text text = (new LiteralText("https://www.digminecraft.com/lists/color_list_pc.php").formatted(Formatting.UNDERLINE).styled((style) -> {
                    return style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,"https://www.digminecraft.com/lists/color_list_pc.php"));
                }));
                MinecraftClient.getInstance().player.sendMessage(text,false);

                return 1;
            }));
        ClientCommandManager.DISPATCHER.register(literal("Colour").then(ClientCommandManager.argument("ColourCode", integer(0, 100000)).executes(context -> {
            colour = context.getArgument("ColourCode",Integer.class);
            jsonFile.write(x,y,colour);
            return 1;


        })));




    }

}
