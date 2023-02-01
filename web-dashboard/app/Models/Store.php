<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Store extends Model
{
    use HasFactory;

    protected $fillable = [
      'user_id' ,'nama_laundry','phone','alamat','longitude','latitude','status'
    ];

    public function user()
    {
        return $this->belongsTo(User::class);
    }

//    public function scopeActive()
//    {
//        return $this->$query = function($query){
//            $query->where('status',1);
//        };
//    }
}
