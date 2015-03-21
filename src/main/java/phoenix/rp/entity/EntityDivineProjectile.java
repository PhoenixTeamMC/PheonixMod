package phoenix.rp.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import phoenix.rp.utility.DivinityHelper;
import phoenix.rp.utility.NBTHelper;

public class EntityDivineProjectile extends EntityThrowable{
    private static final int LIFETIME = 60;
    private int counter = 0;
    public int id;

    public static final int GENESIS_ID = 0;
    public static final int APOCALYPSE_ID = 1;
    public static final int LIBRATUM_ARCUS_ID = 2;

    public EntityDivineProjectile(EntityPlayer player, int id) {
        super(player.getEntityWorld(), player);
        this.id = id;
    }

    @Override
    protected void onImpact(MovingObjectPosition movingObjectPosition) {
        if(movingObjectPosition.entityHit != null && movingObjectPosition.entityHit instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase) movingObjectPosition.entityHit;
            EntityLivingBase origin = getThrower();
            if(origin==null || !(origin instanceof EntityPlayer)) {
                this.setDead();
                return;
            }
            EntityPlayer player = (EntityPlayer) origin;
            if(!(entity instanceof EntityPlayer)) {
                DivinityHelper.smiteEntity(player, entity);
            }
            else if(DivinityHelper.isGod((EntityPlayer) entity)) {
                //Custom god logic
            }
            else {
                DivinityHelper.smiteEntity(player, entity);
            }
        }
        this.setDead();
    }

    @Override
    protected float getGravityVelocity() {
        return 0;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        counter++;
        if(counter==LIFETIME) {
            this.setDead();
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger(NBTHelper.NBT_COUNTER, counter);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        counter = tag.getInteger(NBTHelper.NBT_COUNTER);
    }
}
