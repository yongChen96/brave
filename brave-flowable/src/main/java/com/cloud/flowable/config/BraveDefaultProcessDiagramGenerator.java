package com.cloud.flowable.config;

import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.image.impl.DefaultProcessDiagramCanvas;
import org.flowable.image.impl.DefaultProcessDiagramGenerator;

import java.util.Iterator;
import java.util.List;

/**
 * @author admin
 * @version 1.0
 * @description: flowable自定义
 * @date 2021/7/6 11:09
 */
public class BraveDefaultProcessDiagramGenerator extends DefaultProcessDiagramGenerator {

    @Override
    protected DefaultProcessDiagramCanvas generateProcessDiagram(BpmnModel bpmnModel, String imageType, List<String> highLightedActivities, List<String> highLightedFlows, String activityFontName, String labelFontName, String annotationFontName, ClassLoader customClassLoader, double scaleFactor, boolean drawSequenceFlowNameWithNoLabelDI) {
        this.prepareBpmnModel(bpmnModel);
        DefaultProcessDiagramCanvas processDiagramCanvas = initProcessDiagramCanvas(bpmnModel, imageType, activityFontName, labelFontName, annotationFontName, customClassLoader);
        Iterator var13 = bpmnModel.getPools().iterator();

        while (var13.hasNext()) {
            Pool pool = (Pool) var13.next();
            GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(pool.getId());
            processDiagramCanvas.drawPoolOrLane(pool.getName(), graphicInfo, scaleFactor);
        }

        var13 = bpmnModel.getProcesses().iterator();

        Process process;
        Iterator var22;
        while (var13.hasNext()) {
            process = (Process) var13.next();
            var22 = process.getLanes().iterator();

            while (var22.hasNext()) {
                Lane lane = (Lane) var22.next();
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(lane.getId());
                processDiagramCanvas.drawPoolOrLane(lane.getName(), graphicInfo, scaleFactor);
            }
        }

        var13 = bpmnModel.getProcesses().iterator();

        while (var13.hasNext()) {
            process = (Process) var13.next();
            var22 = process.findFlowElementsOfType(FlowNode.class).iterator();

            while (var22.hasNext()) {
                FlowNode flowNode = (FlowNode) var22.next();
                if (!this.isPartOfCollapsedSubProcess(flowNode, bpmnModel)) {
                    this.drawActivity(processDiagramCanvas, bpmnModel, flowNode, highLightedActivities, highLightedFlows, scaleFactor, drawSequenceFlowNameWithNoLabelDI);
                }
            }
        }

        var13 = bpmnModel.getProcesses().iterator();

        label75:
        while (true) {
            List subProcesses;
            do {
                if (!var13.hasNext()) {
                    return processDiagramCanvas;
                }

                process = (Process) var13.next();
                var22 = process.getArtifacts().iterator();

                while (var22.hasNext()) {
                    Artifact artifact = (Artifact) var22.next();
                    this.drawArtifact(processDiagramCanvas, bpmnModel, artifact);
                }

                subProcesses = process.findFlowElementsOfType(SubProcess.class, true);
            } while (subProcesses == null);

            Iterator var26 = subProcesses.iterator();

            while (true) {
                GraphicInfo graphicInfo;
                SubProcess subProcess;
                do {
                    do {
                        if (!var26.hasNext()) {
                            continue label75;
                        }

                        subProcess = (SubProcess) var26.next();
                        graphicInfo = bpmnModel.getGraphicInfo(subProcess.getId());
                    } while (graphicInfo != null && graphicInfo.getExpanded() != null && !graphicInfo.getExpanded());
                } while (this.isPartOfCollapsedSubProcess(subProcess, bpmnModel));

                Iterator var19 = subProcess.getArtifacts().iterator();

                while (var19.hasNext()) {
                    Artifact subProcessArtifact = (Artifact) var19.next();
                    this.drawArtifact(processDiagramCanvas, bpmnModel, subProcessArtifact);
                }
            }
        }
    }

    protected static DefaultProcessDiagramCanvas initProcessDiagramCanvas(BpmnModel bpmnModel, String imageType, String activityFontName, String labelFontName,
                                                                          String annotationFontName, ClassLoader customClassLoader) {

        // We need to calculate maximum values to know how big the image will be in its entirety
        double minX = Double.MAX_VALUE;
        double maxX = 0;
        double minY = Double.MAX_VALUE;
        double maxY = 0;

        for (Pool pool : bpmnModel.getPools()) {
            GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(pool.getId());
            minX = graphicInfo.getX();
            maxX = graphicInfo.getX() + graphicInfo.getWidth();
            minY = graphicInfo.getY();
            maxY = graphicInfo.getY() + graphicInfo.getHeight();
        }

        List<FlowNode> flowNodes = gatherAllFlowNodes(bpmnModel);
        for (FlowNode flowNode : flowNodes) {

            GraphicInfo flowNodeGraphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());

            // width
            if (flowNodeGraphicInfo.getX() + flowNodeGraphicInfo.getWidth() > maxX) {
                maxX = flowNodeGraphicInfo.getX() + flowNodeGraphicInfo.getWidth();
            }
            if (flowNodeGraphicInfo.getX() < minX) {
                minX = flowNodeGraphicInfo.getX();
            }
            // height
            if (flowNodeGraphicInfo.getY() + flowNodeGraphicInfo.getHeight() > maxY) {
                maxY = flowNodeGraphicInfo.getY() + flowNodeGraphicInfo.getHeight();
            }
            if (flowNodeGraphicInfo.getY() < minY) {
                minY = flowNodeGraphicInfo.getY();
            }

            for (SequenceFlow sequenceFlow : flowNode.getOutgoingFlows()) {
                List<GraphicInfo> graphicInfoList = bpmnModel.getFlowLocationGraphicInfo(sequenceFlow.getId());
                if (graphicInfoList != null) {
                    for (GraphicInfo graphicInfo : graphicInfoList) {
                        // width
                        if (graphicInfo.getX() > maxX) {
                            maxX = graphicInfo.getX();
                        }
                        if (graphicInfo.getX() < minX) {
                            minX = graphicInfo.getX();
                        }
                        // height
                        if (graphicInfo.getY() > maxY) {
                            maxY = graphicInfo.getY();
                        }
                        if (graphicInfo.getY() < minY) {
                            minY = graphicInfo.getY();
                        }
                    }
                }
            }
        }

        List<Artifact> artifacts = gatherAllArtifacts(bpmnModel);
        for (Artifact artifact : artifacts) {

            GraphicInfo artifactGraphicInfo = bpmnModel.getGraphicInfo(artifact.getId());

            if (artifactGraphicInfo != null) {
                // width
                if (artifactGraphicInfo.getX() + artifactGraphicInfo.getWidth() > maxX) {
                    maxX = artifactGraphicInfo.getX() + artifactGraphicInfo.getWidth();
                }
                if (artifactGraphicInfo.getX() < minX) {
                    minX = artifactGraphicInfo.getX();
                }
                // height
                if (artifactGraphicInfo.getY() + artifactGraphicInfo.getHeight() > maxY) {
                    maxY = artifactGraphicInfo.getY() + artifactGraphicInfo.getHeight();
                }
                if (artifactGraphicInfo.getY() < minY) {
                    minY = artifactGraphicInfo.getY();
                }
            }

            List<GraphicInfo> graphicInfoList = bpmnModel.getFlowLocationGraphicInfo(artifact.getId());
            if (graphicInfoList != null) {
                for (GraphicInfo graphicInfo : graphicInfoList) {
                    // width
                    if (graphicInfo.getX() > maxX) {
                        maxX = graphicInfo.getX();
                    }
                    if (graphicInfo.getX() < minX) {
                        minX = graphicInfo.getX();
                    }
                    // height
                    if (graphicInfo.getY() > maxY) {
                        maxY = graphicInfo.getY();
                    }
                    if (graphicInfo.getY() < minY) {
                        minY = graphicInfo.getY();
                    }
                }
            }
        }

        int nrOfLanes = 0;
        for (Process process : bpmnModel.getProcesses()) {
            for (Lane l : process.getLanes()) {

                nrOfLanes++;

                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(l.getId());
                // // width
                if (graphicInfo.getX() + graphicInfo.getWidth() > maxX) {
                    maxX = graphicInfo.getX() + graphicInfo.getWidth();
                }
                if (graphicInfo.getX() < minX) {
                    minX = graphicInfo.getX();
                }
                // height
                if (graphicInfo.getY() + graphicInfo.getHeight() > maxY) {
                    maxY = graphicInfo.getY() + graphicInfo.getHeight();
                }
                if (graphicInfo.getY() < minY) {
                    minY = graphicInfo.getY();
                }
            }
        }

        // Special case, see https://activiti.atlassian.net/browse/ACT-1431
        if (flowNodes.isEmpty() && bpmnModel.getPools().isEmpty() && nrOfLanes == 0) {
            // Nothing to show
            minX = 0;
            minY = 0;
        }

        return new BraveDefaultProcessDiagramCanvas((int) maxX + 10, (int) maxY + 10, (int) minX, (int) minY,
                imageType, activityFontName, labelFontName, annotationFontName, customClassLoader);
    }


    private static void drawHighLight(DefaultProcessDiagramCanvas processDiagramCanvas, GraphicInfo graphicInfo) {
        processDiagramCanvas.drawHighLight((int) graphicInfo.getX(), (int) graphicInfo.getY(), (int) graphicInfo.getWidth(), (int) graphicInfo.getHeight());

    }

    private static void drawHighLigh1(BraveDefaultProcessDiagramCanvas processDiagramCanvas, GraphicInfo graphicInfo) {
        processDiagramCanvas.drawHighLight1((int) graphicInfo.getX(), (int) graphicInfo.getY(), (int) graphicInfo.getWidth(), (int) graphicInfo.getHeight());

    }

    protected void drawActivity(DefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel,
                                FlowNode flowNode, java.util.List<String> highLightedActivities, java.util.List<String> highLightedFlows, double scaleFactor, Boolean drawSequenceFlowNameWithNoLabelDI) {

        DefaultProcessDiagramGenerator.ActivityDrawInstruction drawInstruction = activityDrawInstructions.get(flowNode.getClass());
        if (drawInstruction != null) {

            drawInstruction.draw(processDiagramCanvas, bpmnModel, flowNode);

            // Gather info on the multi instance marker
            boolean multiInstanceSequential = false;
            boolean multiInstanceParallel = false;
            boolean collapsed = false;
            if (flowNode instanceof Activity) {
                Activity activity = (Activity) flowNode;
                MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = activity.getLoopCharacteristics();
                if (multiInstanceLoopCharacteristics != null) {
                    multiInstanceSequential = multiInstanceLoopCharacteristics.isSequential();
                    multiInstanceParallel = !multiInstanceSequential;
                }
            }

            // Gather info on the collapsed marker
            GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
            if (flowNode instanceof SubProcess) {
                collapsed = graphicInfo.getExpanded() != null && !graphicInfo.getExpanded();
            } else if (flowNode instanceof CallActivity) {
                collapsed = true;
            }

            if (scaleFactor == 1.0) {
                // Actually draw the markers
                processDiagramCanvas.drawActivityMarkers((int) graphicInfo.getX(), (int) graphicInfo.getY(), (int) graphicInfo.getWidth(), (int) graphicInfo.getHeight(),
                        multiInstanceSequential, multiInstanceParallel, collapsed);
            }

            // Draw highlighted activities
            if (highLightedActivities.contains(flowNode.getId())) {

                if (highLightedActivities.get(highLightedActivities.size() - 1).equals(flowNode.getId())
                        && !flowNode.getId().equals("endenv")) {
                    //更改源码没有走的红色表示
                    drawHighLigh1((BraveDefaultProcessDiagramCanvas) processDiagramCanvas, bpmnModel.getGraphicInfo(flowNode.getId()));
                } else {
                    drawHighLight(processDiagramCanvas, bpmnModel.getGraphicInfo(flowNode.getId()));
                }


            }

        }

        for (SequenceFlow sequenceFlow : flowNode.getOutgoingFlows()) {
            boolean highLighted = (highLightedFlows.contains(sequenceFlow.getId()));
            String defaultFlow = null;
            if (flowNode instanceof Activity) {
                defaultFlow = ((Activity) flowNode).getDefaultFlow();
            } else if (flowNode instanceof Gateway) {
                defaultFlow = ((Gateway) flowNode).getDefaultFlow();
            }

            boolean isDefault = false;
            if (defaultFlow != null && defaultFlow.equalsIgnoreCase(sequenceFlow.getId())) {
                isDefault = true;
            }
            boolean drawConditionalIndicator = sequenceFlow.getConditionExpression() != null && !(flowNode instanceof Gateway);

            String sourceRef = sequenceFlow.getSourceRef();
            String targetRef = sequenceFlow.getTargetRef();
            FlowElement sourceElement = bpmnModel.getFlowElement(sourceRef);
            FlowElement targetElement = bpmnModel.getFlowElement(targetRef);
            java.util.List<GraphicInfo> graphicInfoList = bpmnModel.getFlowLocationGraphicInfo(sequenceFlow.getId());
            if (graphicInfoList != null && graphicInfoList.size() > 0) {
                graphicInfoList = connectionPerfectionizer(processDiagramCanvas, bpmnModel, sourceElement, targetElement, graphicInfoList);
                int xPoints[] = new int[graphicInfoList.size()];
                int yPoints[] = new int[graphicInfoList.size()];

                for (int i = 1; i < graphicInfoList.size(); i++) {
                    GraphicInfo graphicInfo = graphicInfoList.get(i);
                    GraphicInfo previousGraphicInfo = graphicInfoList.get(i - 1);

                    if (i == 1) {
                        xPoints[0] = (int) previousGraphicInfo.getX();
                        yPoints[0] = (int) previousGraphicInfo.getY();
                    }
                    xPoints[i] = (int) graphicInfo.getX();
                    yPoints[i] = (int) graphicInfo.getY();

                }

                processDiagramCanvas.drawSequenceflow(xPoints, yPoints, drawConditionalIndicator, isDefault, highLighted, scaleFactor);


                // Draw sequenceflow label
                GraphicInfo labelGraphicInfo = bpmnModel.getLabelGraphicInfo(sequenceFlow.getId());
                if (labelGraphicInfo != null) {
                    processDiagramCanvas.drawLabel(sequenceFlow.getName(), labelGraphicInfo, false);
                } else {
                    if (drawSequenceFlowNameWithNoLabelDI) {
                        GraphicInfo lineCenter = getLineCenter(graphicInfoList);
                        processDiagramCanvas.drawLabel(sequenceFlow.getName(), lineCenter, false);
                    }

                }
            }
        }

        if (flowNode instanceof FlowElementsContainer) {
            for (FlowElement nestedFlowElement : ((FlowElementsContainer) flowNode).getFlowElements()) {
                if (nestedFlowElement instanceof FlowNode && !isPartOfCollapsedSubProcess(nestedFlowElement, bpmnModel)) {
                    drawActivity(processDiagramCanvas, bpmnModel, (FlowNode) nestedFlowElement,
                            highLightedActivities, highLightedFlows, scaleFactor, drawSequenceFlowNameWithNoLabelDI);
                }
            }
        }
    }
}
