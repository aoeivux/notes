#!/bin/bash
dataset="voc"
iters=200
if [ $dataset = "voc" ]
then
    data_dir="./data/VOCdevkit/VOC2012/"
elif [ $dataset = "coco" ]
then
    data_dir="./data/coco2017/"
fi
python train.py --use-cuda --iters ${iters} --dataset ${dataset} --data-dir ${data_dir}

